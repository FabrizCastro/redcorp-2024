using Redcorp.Services.WorkAndProyectAPI.Context;
using Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Infraestructure
{
    public class SectionInfraestructure : ISectionInfraestructure
    {
        private WorkAndProyectDbContext _workAndProyectDbContext;

        public SectionInfraestructure(WorkAndProyectDbContext workAndProyectDbContext)
        {
            _workAndProyectDbContext = workAndProyectDbContext;
        }

        public bool delete(int id)
        {
            Section section = _workAndProyectDbContext.Sections.Find(id);
            section.IsActive = false;
            _workAndProyectDbContext.Sections.Update(section);
            _workAndProyectDbContext.SaveChanges();
            return true;

        }

        public async Task<bool> DeleteAsync(int id)
        {
            try
            {
                Section section = await _workAndProyectDbContext.Sections.FindAsync(id);
                section.IsActive = false;
                _workAndProyectDbContext.Sections.Update(section);
                await _workAndProyectDbContext.SaveChangesAsync();
                return true;
            }
            catch (Exception e)
            {
                throw new Exception("Error al eliminar la seccion de la base de datos", e);
            }

        }

        public async Task<List<Section>> GetAllAsync()
        {
            try
            {
                return await _workAndProyectDbContext.Sections.Where(section => section.IsActive).ToListAsync();
            }
            catch (Exception e)
            {
                throw new Exception("Error al obtener todas las secciones de la base de datos", e);
            }
        }

        public Section GetById(int id)
        {
            return _workAndProyectDbContext.Sections.Find(id);
        }

        public async Task<Section> GetByIdAsync(int id)
        {
            try
            {
                return await _workAndProyectDbContext.Sections.FindAsync(id);
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener la sección con el id especificado en la base de datos", ex);
            }

        }



        public bool Save(Section section)
        {
            try
            {
                _workAndProyectDbContext.Sections.Add(section);
                _workAndProyectDbContext.SaveChanges();
            }
            catch (Exception ex)
            {
                throw new Exception("Error al guardar la sección en la base de datos.", ex);
            }
            return true;
        }

        public async Task<bool> SaveAsync(Section section)
        {
            try
            {
                await _workAndProyectDbContext.Sections.AddAsync(section);
                await _workAndProyectDbContext.SaveChangesAsync();
                return true;
            }
            catch (Exception ex)
            {
                throw new Exception("Error al guardar la sección en la base de datos.", ex);
            }

        }

        public bool update(int id, string section_name, string description)
        {
            Section _section = _workAndProyectDbContext.Sections.Find(id);
            _section.Section_Name = section_name;
            _section.Description = description;

            _workAndProyectDbContext.Sections.Update(_section);
            _workAndProyectDbContext.SaveChanges();

            return true;
        }

        public async Task<bool> UpdateAsync(int id, string section_name, string description)
        {
            try
            {
                Section section = await _workAndProyectDbContext.Sections.FindAsync(id);
                section.Section_Name = section_name;
                section.Description = description;

                _workAndProyectDbContext.Sections.Update(section);
                await _workAndProyectDbContext.SaveChangesAsync();
                return true;
            }
            catch (Exception e)
            {
                throw new Exception("Error al actualizar la sección en la base de datos.", e);
            }
        }
    }
}
