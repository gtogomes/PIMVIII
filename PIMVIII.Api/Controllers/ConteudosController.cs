using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PIMVIII.Api.Data;
using PIMVIII.Api.Repositories;
using PIMVIII.Api.Models;


[ApiController]
[Route("api/[controller]")]
public class ConteudosController : ControllerBase
{
    private readonly StreamingContext _context;

    public ConteudosController(StreamingContext context)
    {
        _context = context;
    }

    [HttpGet]
    public IActionResult GetAll() => Ok(_context.Conteudos.Include(c => c.Criador).ToList());

    [HttpGet("{id:int}")]
    public IActionResult Get(int id)
    {
        var c = _context.Conteudos.Include(x => x.Criador).FirstOrDefault(x => x.ID == id);
        if (c == null) return NotFound();
        return Ok(c);
    }

    [HttpPost]
    public IActionResult Create(Conteudo c)
    {
        _context.Conteudos.Add(c);
        _context.SaveChanges();
        return CreatedAtAction(nameof(Get), new { id = c.ID }, c);
    }
}
