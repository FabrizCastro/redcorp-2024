using AutoMapper;
using Microsoft.EntityFrameworkCore;
using Redcorp.Services.ProfileAPI.Context;
using Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.ProfileAPI.Models;

namespace Redcorp.Services.ProfileAPI.Infraestructure
{
    public class ProfileInfraestructure : IProfileInfraestructure
    {
        private ProfileDbContext _profileDbContext;

        public ProfileInfraestructure(ProfileDbContext profileDbContext)
        {
            _profileDbContext = profileDbContext;
        }
        public async Task<bool> DeleteAsync(int id)
        {
            try
            {
                Employee employee = await _profileDbContext.Employees.FindAsync(id);
                if (employee == null)
                    return false;

                employee.isActive = false;
                _profileDbContext.Employees.Update(employee);
                await _profileDbContext.SaveChangesAsync();
                return true;
            }
            catch (Exception ex)
            {
                throw new Exception("Error al eliminar el empleado.", ex);
            }
            
        }

        public async Task<List<Employee>> GetAllAsync()
        {
            try
            {
                return await _profileDbContext.Employees.Where(employee => employee.isActive).ToListAsync();
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener los empleados.", ex);
            }
        }

        public async Task<Employee> GetByEmailAsync(string email)
        {
            try
            {
                return await _profileDbContext.Employees.FirstOrDefaultAsync(e => e.email == email);
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener el email del empleado.", ex);
            }

        }

        public async Task<Employee> GetByIdAsync(int id)
        {
            try
            {
                return await _profileDbContext.Employees.FindAsync(id);
            }
            catch (Exception ex) 
            {
                throw new Exception("Error al obtener empleado.", ex);
            }
        }

        public async Task<Employee> GetByLoginAsync(string email, string password)
        {
            try
            {
                Employee employee = await _profileDbContext.Employees.FirstOrDefaultAsync(x => x.email == email && x.password == password);
                return employee;
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener login.", ex);
            }
        }

        public async Task<bool> SaveAsync(Employee employee)
        {
            try
            {
                if (employee.cargo == "Supervisor" && employee.cargo == "Gerente")
                {
                    employee.rol = "admin";
                }
                else
                {
                    employee.rol = "user";
                }

                employee.isActive = true;
                await _profileDbContext.Employees.AddAsync(employee);
                await _profileDbContext.SaveChangesAsync();
                return true;
            }
            catch (Exception ex)
            {
                throw new Exception("Error al guardar el empleado.", ex);
            }
        }

        public async Task<int> SignupAsync(Employee employee)
        {
            try
            {
                if (employee.cargo == "Supervisor" || employee.cargo == "Gerente")
                {
                    employee.rol = "admin";
                }
                else
                {
                    employee.rol = "user";
                }

                employee.isActive = true;
                await _profileDbContext.Employees.AddAsync(employee);
                await _profileDbContext.SaveChangesAsync();
                return employee.id;
            }
            catch(Exception ex)
            {
                throw new Exception("Error al registrar empleado.", ex);
            }
        }

        public async Task<bool> UpdateAsync(int id,Employee employee)
        {
            try
            {
                Employee _employee = await _profileDbContext.Employees.FirstOrDefaultAsync(x => x.id == id);
                if (_employee == null)
                    return false;

                _employee.name = employee.name;
                _employee.last_name = employee.last_name;
                _employee.email = employee.email;
                _employee.area = employee.area;
                _employee.cargo = employee.cargo;
                _employee.photo = employee.photo;

                _profileDbContext.Employees.Update(_employee);
                await _profileDbContext.SaveChangesAsync();
                return true;

            }
            catch (Exception ex) 
            {
                throw new Exception("Error al actualizar empleado.", ex);

            }

        }
    }
}
