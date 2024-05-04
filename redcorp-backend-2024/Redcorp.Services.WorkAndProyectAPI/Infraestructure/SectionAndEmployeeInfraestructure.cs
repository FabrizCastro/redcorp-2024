using Microsoft.EntityFrameworkCore;
using Redcorp.Services.WorkAndProyectAPI.Context;
using Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.WorkAndProyectAPI.Models;
using Redcorp.Services.WorkAndProyectAPI.Models.Dto;

namespace Redcorp.Services.WorkAndProyectAPI.Infraestructure
{
    public class SectionAndEmployeeInfraestructure : ISectionAndEmployeeInfraestructure
    {
        private WorkAndProyectDbContext _workAndProyectDbContext;

        public SectionAndEmployeeInfraestructure(WorkAndProyectDbContext workAndProyectDbContext)
        {
            workAndProyectDbContext = _workAndProyectDbContext;
        }

        public async Task<List<SectionAndEmployee>> GetAllAsync()
        {
            try
            {
                return await _workAndProyectDbContext.SectionsAndEmployees.Where(SE => SE.IsActive).ToListAsync();
            }
            catch (Exception e)
            {
                throw new Exception("Error al obtener los SectionAndEmployee de la base de datos.", e);
            }

        }

        public async Task<SectionAndEmployee> GetByIdAsync(int id)
        {
            try
            {
                return await _workAndProyectDbContext.SectionsAndEmployees.FindAsync(id);
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener el SectionAndEmployee con el id especificado en la base de datos.", ex);
            }

        }

        public async Task<bool> SaveAsync(SectionAndEmployee sectionAndEmployees)
        {
            try
            {
                if (await existsSectionIdAndEmployeeId(sectionAndEmployees))
                {
                    await _workAndProyectDbContext.SectionsAndEmployees.AddAsync(sectionAndEmployees);
                    await _workAndProyectDbContext.SaveChangesAsync();
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                throw new Exception("Error al guardar SectionAndEmployee en la base de datos.", ex);
            }

        }

        public async Task<bool> UpdateAsync(int id, int Section_Id, int Employee_Id)
        {
            try
            {
                SectionAndEmployee _sectionsAndEmployees = await _workAndProyectDbContext.SectionsAndEmployees.FindAsync(id);
                _sectionsAndEmployees.Section_Id = Section_Id;
                _sectionsAndEmployees.Employees_Id = Employee_Id;

                _workAndProyectDbContext.SectionsAndEmployees.Update(_sectionsAndEmployees);
                await _workAndProyectDbContext.SaveChangesAsync();

                return true;
            }
            catch (Exception e)
            {
                throw new Exception("Error al actualizar SectionAndEmployee en la base de datos.", e);
            }

        }
        public async Task<bool> DeleteAsync(int id)
        {
            try
            {
                SectionAndEmployee sectionsAndEmployees = await _workAndProyectDbContext.SectionsAndEmployees.FindAsync(id);
                sectionsAndEmployees.IsActive = false;
                _workAndProyectDbContext.SectionsAndEmployees.Update(sectionsAndEmployees);
                await _workAndProyectDbContext.SaveChangesAsync();
                return true;
            }
            catch (Exception e)
            {
                throw new Exception("Error al eliminar SectionAndEmployee en la base de datos.", e);
            }
        }

        public async Task<bool> existsSectionIdAndEmployeeId(SectionAndEmployee sectionAndEmployee)
        {
            //bool employeeExists = await _workAndProyectDbContext.Employees.AnyAsync(e => e.Id == sectionAndEmployee.Employees_Id);
            bool sectionExists = await _workAndProyectDbContext.Sections.AnyAsync(s => s.Id == sectionAndEmployee.Section_Id);
            //return employeeExists && sectionExists;
            return sectionExists;
        }
        public async Task<List<EmployeeDto>> GetEmployeesBySectionId(int sectionId)
        {
            try
            {
                List<int> employeeIds = await _workAndProyectDbContext.SectionsAndEmployees
                    .Where(se => se.Section_Id == sectionId)
                    .Select(se => se.Employees_Id)
                    .ToListAsync();

                List<EmployeeDto> employees = null;

                /*List<EmployeeDto> employees = await _workAndProyectDbContext.Employees
                    .Where(e => employeeIds.Contains(e.Id))
                    .ToListAsync();*/

                return employees;
            }
            catch (Exception e)
            {
                throw new Exception("Error al obtener empleados con el id especificado en la base de datos.", e);
            }

        }
        public async Task<List<Models.Section>> GetSectionsByEmployeeId(int employeeId)
        {
            try
            {
                List<Models.Section> sections = new List<Models.Section>();

                List<SectionAndEmployee> sectionAndEmployees = await _workAndProyectDbContext.SectionsAndEmployees
                    .Where(se => se.Employees_Id == employeeId)
                    .ToListAsync();

                foreach (SectionAndEmployee sectionAndEmployee in sectionAndEmployees)
                {
                    Models.Section section = await _workAndProyectDbContext.Sections.FirstOrDefaultAsync(s => s.Id == sectionAndEmployee.Section_Id);
                    if (section != null)
                    {
                        sections.Add(section);
                    }
                }

                return sections;
            }
            catch (Exception e)
            {
                throw new Exception("Error al buscar Section con el id especificado en la base de datos", e);
            }

        }
    }
}
