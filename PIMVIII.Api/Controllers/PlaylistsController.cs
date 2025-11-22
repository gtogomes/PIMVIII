using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using PIMVIII.Api.Data;
using PIMVIII.Api.Repositories;
using PIMVIII.Api.Models;

[ApiController]
[Route("api/[controller]")]
public class PlaylistsController : ControllerBase
{
    private readonly PlaylistRepository _repository;
    private readonly StreamingContext _context;

    public PlaylistsController(PlaylistRepository repository, StreamingContext context)
    {
        _repository = repository;
        _context = context;
    }

    [HttpGet]
    public IActionResult GetAll()
    {
        return Ok(_repository.GetAllPlaylists());
    }

    [HttpGet("{id:int}")]
    public IActionResult Get(int id)
    {
        var p = _repository.GetPlaylistByID(id);
        if (p == null) return NotFound();
        return Ok(p);
    }

    [Authorize]
    [HttpPost]
    public IActionResult Create(CreatePlaylistDto dto)
    {
        var user = _context.Usuarios.Find(dto.UsuarioID);
        if (user == null) return BadRequest("Usuario não encontrado");

        var playlist = new Playlist { Nome = dto.Nome, UsuarioID = dto.UsuarioID };
        _repository.AddPlaylist(playlist);

        // adicionar items
        foreach (var cid in dto.ConteudoIds)
        {
            var c = _context.Conteudos.Find(cid);
            if (c != null)
                _context.ItemPlaylists.Add(new ItemPlaylist { PlaylistID = playlist.ID, ConteudoID = c.ID });
        }

        _context.SaveChanges();

        return CreatedAtAction(nameof(Get), new { id = playlist.ID }, playlist);
    }

    [Authorize]
    [HttpPut("{id:int}")]
    public IActionResult Update(int id, CreatePlaylistDto dto)
    {
        var existing = _repository.GetPlaylistByID(id);
        if (existing == null) return NotFound();

        existing.Nome = dto.Nome;
        existing.UsuarioID = dto.UsuarioID;

        // rebuild items for simplicity
        var items = _context.ItemPlaylists.Where(i => i.PlaylistID == id).ToList();
        _context.ItemPlaylists.RemoveRange(items);
        foreach (var cid in dto.ConteudoIds)
        {
            _context.ItemPlaylists.Add(new ItemPlaylist { PlaylistID = id, ConteudoID = cid });
        }

        _repository.UpdatePlaylist(existing);
        return NoContent();
    }

    [Authorize]
    [HttpDelete("{id:int}")]
    public IActionResult Delete(int id)
    {
        _repository.DeletePlaylist(id);
        return NoContent();
    }
}
