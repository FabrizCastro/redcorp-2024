using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure
{
    public interface ISectionInfraestructure
    {
        Section GetById(int id);
        public bool Save(Section section);
        public bool update(int id, string section_name, string description);
        public bool delete(int id);
        Task<List<Section>> GetAllAsync();
        Task<Section> GetByIdAsync(int id);
        Task<bool> SaveAsync(Section section);
        Task<bool> UpdateAsync(int id, string section_name, string description);
        Task<bool> DeleteAsync(int id);
    }
}
