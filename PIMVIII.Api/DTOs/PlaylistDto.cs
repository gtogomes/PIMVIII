using System.Collections.Generic;

public class PlaylistDto
{
    public int ID { get; set; }
    public string Nome { get; set; } = null!;
    public int UsuarioID { get; set; }
    public List<int> ConteudoIds { get; set; } = new();
}
