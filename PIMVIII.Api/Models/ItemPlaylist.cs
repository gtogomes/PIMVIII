namespace PIMVIII.Api.Models;

public class ItemPlaylist
{
    public int ID { get; set; }

    public int PlaylistID { get; set; }
    public Playlist? Playlist { get; set; }

    public int ConteudoID { get; set; }
    public Conteudo? Conteudo { get; set; }
}
