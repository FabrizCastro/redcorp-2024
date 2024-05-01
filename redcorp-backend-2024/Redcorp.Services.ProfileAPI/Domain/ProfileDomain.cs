using Redcorp.Services.ProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.ProfileAPI.Models;

namespace Redcorp.Services.ProfileAPI.Domain
{
    public class ProfileDomain : IProfileDomain
    {
        private IProfileInfraestructure _profileInfraestructure;

        public ProfileDomain(IProfileInfraestructure profileInfraestructure) 
        {
            _profileInfraestructure = profileInfraestructure;
        }

        public async Task<bool> DeleteAsync(int id)
        {
            return await _profileInfraestructure.DeleteAsync(id);
        }

        public async Task<Employee> GetByEmailAsync(string username)
        {
            return await _profileInfraestructure.GetByEmailAsync(username);
        }

        public Task<string> LoginAsync(Employee employee)
        {
            throw new NotImplementedException();
        }

        public async Task<bool> SaveAsync(Employee employee)
        {
            if (!this.IsValidData(employee.name, employee.last_name)) throw new Exception("La longitud del nombre es inválida");
            if (employee.name?.Length > 20) throw new Exception("La longitud del nombre es muy grande");

            return await _profileInfraestructure.SaveAsync(employee);
        }

        public Task<int> SignupAsync(Employee employee)
        {
            throw new NotImplementedException();
        }

        public Task<bool> UpdateAsync(Employee employee)
        {
            if (!this.IsValidData(employee.name, employee.last_name)) throw new Exception("La longitud del nombre es inválida");
            return _profileInfraestructure.UpdateAsync(employee);
        }

        private bool IsValidData(string? name, string? last_name)
        {
            if (name?.Length < 3 || last_name?.Length < 3) return false;
            return true;
        }
    }
}
