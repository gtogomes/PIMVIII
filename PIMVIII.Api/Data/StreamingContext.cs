using Microsoft.EntityFrameworkCore;
using PIMVIII.Api.Models;

namespace PIMVIII.Api.Data;

public class StreamingContext : DbContext
{
    public StreamingContext(DbContextOptions<StreamingContext> options)
        : base(options)
    {
    }

    public DbSet<Usuario> Usuarios { get; set; }
    public DbSet<Criador> Criadores { get; set; }
    public DbSet<Conteudo> Conteudos { get; set; }
    public DbSet<Playlist> Playlists { get; set; }
    public DbSet<ItemPlaylist> ItemPlaylists { get; set; }
}
