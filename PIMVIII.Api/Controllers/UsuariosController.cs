using Microsoft.AspNetCore.Mvc;
using PIMVIII.Api.Data;
using PIMVIII.Api.Repositories;
using PIMVIII.Api.Models;

[ApiController]
[Route("api/[controller]")]
public class UsuariosController : ControllerBase
{
    private readonly StreamingContext _context;

    public UsuariosController(StreamingContext context)
    {
        _context = context;
    }

    [HttpGet]
    public IActionResult GetAll() => Ok(_context.Usuarios.ToList());

    [HttpGet("{id:int}")]
    public IActionResult Get(int id)
    {
        var u = _context.Usuarios.Find(id);
        if (u == null) return NotFound();
        return Ok(u);
    }

    [HttpPost]
    public IActionResult Create(Usuario u)
    {
        // Em produção: valide email, use hash e salt
        _context.Usuarios.Add(u);
        _context.SaveChanges();
        return CreatedAtAction(nameof(Get), new { id = u.ID }, u);
    }
}
