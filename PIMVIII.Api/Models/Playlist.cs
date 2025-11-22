using Microsoft.IdentityModel.Tokens;
using System.Text;

namespace PIMVIII.Api.Models;

public class Playlist
{
    public int ID { get; set; }
    public string Nome { get; set; } = string.Empty;

    public int UsuarioID { get; set; }
    public Usuario? Usuario { get; set; }

    public List<ItemPlaylist> Items { get; set; } = new();
}
