using PIMVIII.Api.Models;
using System.Linq;


namespace PIMVIII.Api.Data
{
    public static class DbSeed
    {
        public static void Seed(StreamingContext context)
        {
            // Se já existem dados, não insere novamente
            if (context.Usuarios.Any())
                return;

            var usuario = new Usuario
            {
                Nome = "Usuário Demo",
                Email = "demo@example.com",
                PasswordHash = "1234"
            };

            var criador = new Criador
            {
                Nome = "Criador Inicial"
            };

            var conteudo = new Conteudo
            {
                Titulo = "Primeiro Vídeo",
                Tipo = "video",
                Criador = criador
            };

            var playlist = new Playlist
            {
                Nome = "Minha Playlist",
                Usuario = usuario
            };

            context.Usuarios.Add(usuario);
            context.Criadores.Add(criador);
            context.Conteudos.Add(conteudo);
            context.Playlists.Add(playlist);

            context.SaveChanges();
        }
    }
}
