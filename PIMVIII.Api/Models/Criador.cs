using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

public class Criador
{
    public int ID { get; set; }

    [Required]
    public string Nome { get; set; } = null!;

    public List<Conteudo> Conteudos { get; set; } = new();
}
