using Microsoft.AspNetCore.Mvc;
using PIMVIII.Api.Data;
using PIMVIII.Api.Repositories;
using PIMVIII.Api.Models;

[ApiController]
[Route("api/[controller]")]
public class CriadoresController : ControllerBase
{
    private readonly StreamingContext _context;

    public CriadoresController(StreamingContext context)
    {
        _context = context;
    }

    [HttpGet]
    public IActionResult GetAll() => Ok(_context.Criadores.ToList());

    [HttpPost]
    public IActionResult Create(Criador cr)
    {
        _context.Criadores.Add(cr);
        _context.SaveChanges();
        return CreatedAtAction(nameof(GetAll), null);
    }
}
