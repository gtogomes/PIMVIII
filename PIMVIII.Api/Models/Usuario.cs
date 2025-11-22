namespace PIMVIII.Api.Models;

public class Usuario
{
    public int ID { get; set; }
    public string Nome { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;

    // Necessário para AuthController
    public string PasswordHash { get; set; } = string.Empty;

    public List<Playlist> Playlists { get; set; } = new();
}
