using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using PIMVIII.Api.Data;
using PIMVIII.Api.Models;

namespace PIMVIII.Api.Controllers;

[ApiController]
[Route("api/[controller]")]
public class AuthController : ControllerBase
{
    private readonly StreamingContext _context;
    private readonly IConfiguration _config;

    public AuthController(StreamingContext context, IConfiguration config)
    {
        _context = context;
        _config = config;
    }

    [HttpPost("login")]
    public IActionResult Login([FromBody] dynamic model)
    {
        string email = model.email;
        string password = model.password;

        var user = _context.Usuarios.FirstOrDefault(u =>
            u.Email == email && u.PasswordHash == password);

        if (user == null)
            return Unauthorized(new { message = "Credenciais inválidas" });

        var key = Encoding.UTF8.GetBytes(_config["Jwt:Key"]!);

        var creds = new SigningCredentials(
            new SymmetricSecurityKey(key),
            SecurityAlgorithms.HmacSha256
        );

        var token = new JwtSecurityToken(
            issuer: _config["Jwt:Issuer"],
            audience: _config["Jwt:Audience"],
            claims: new[]
            {
                new Claim(ClaimTypes.NameIdentifier, user.ID.ToString()),
                new Claim(ClaimTypes.Email, user.Email),
                new Claim(ClaimTypes.Name, user.Nome),
            },
            expires: DateTime.UtcNow.AddHours(1),
            signingCredentials: creds
        );

        var jwt = new JwtSecurityTokenHandler().WriteToken(token);

        return Ok(new { token = jwt });
    }
}
