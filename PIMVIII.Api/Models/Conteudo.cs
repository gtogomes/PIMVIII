using System.ComponentModel.DataAnnotations;

public class Conteudo
{
    public int ID { get; set; }

    [Required]
    public string Titulo { get; set; } = null!;

    public string Tipo { get; set; } = "video"; // video, audio, podcast

    public int CriadorID { get; set; }
    public Criador? Criador { get; set; }
}
