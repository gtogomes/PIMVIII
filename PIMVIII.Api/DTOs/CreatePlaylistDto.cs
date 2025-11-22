public class CreatePlaylistDto
{
    public string Nome { get; set; } = null!;
    public int UsuarioID { get; set; }
    public List<int> ConteudoIds { get; set; } = new();
}
