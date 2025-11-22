using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using PIMVIII.Api.Data;
using PIMVIII.Api.Models;

namespace PIMVIII.Api.Repositories
{
    public class PlaylistRepository
    {
        private readonly StreamingContext _context;

        public PlaylistRepository(StreamingContext context)
        {
            _context = context;
        }

        public List<Playlist> GetAllPlaylists()
        {
            return _context.Playlists
                .Include(p => p.Items)
                    .ThenInclude(i => i.Conteudo)
                .Include(p => p.Usuario)
                .ToList();
        }

        public Playlist? GetPlaylistByID(int id)
        {
            return _context.Playlists
                .Include(p => p.Items)
                    .ThenInclude(i => i.Conteudo)
                .Include(p => p.Usuario)
                .FirstOrDefault(p => p.ID == id);
        }

        public void AddPlaylist(Playlist playlist)
        {
            _context.Playlists.Add(playlist);
            _context.SaveChanges();
        }

        public void UpdatePlaylist(Playlist playlist)
        {
            _context.Playlists.Update(playlist);
            _context.SaveChanges();
        }

        public void DeletePlaylist(int id)
        {
            var playlist = GetPlaylistByID(id);
            if (playlist != null)
            {
                _context.Playlists.Remove(playlist);
                _context.SaveChanges();
            }
        }
    }
}
