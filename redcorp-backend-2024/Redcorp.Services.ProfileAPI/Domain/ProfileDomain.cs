using Redcorp.Services.AuthAndProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Domain.I.Domain;
using Redcorp.Services.ProfileAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.ProfileAPI.Models;

namespace Redcorp.Services.ProfileAPI.Domain
{
    public class ProfileDomain : IProfileDomain
    {
        private IProfileInfraestructure _profileInfraestructure;
        private IEncryptDomain _encryptDomain;
        private ITokenDomain _tokenDomain;
        public ProfileDomain(IProfileInfraestructure profileInfraestructure, IEncryptDomain encryptDomain, ITokenDomain tokenDomain) 
        {
            _profileInfraestructure = profileInfraestructure;
            _encryptDomain = encryptDomain;
            _tokenDomain = tokenDomain;
        }

        public async Task<bool> DeleteAsync(int id)
        {
            return await _profileInfraestructure.DeleteAsync(id);
        }

        public async Task<Employee> GetByEmailAsync(string username)
        {
            return await _profileInfraestructure.GetByEmailAsync(username);
        }

        public async Task<string> LoginAsync(Employee employee)
        {
            var foundUser = await _profileInfraestructure.GetByEmailAsync(employee.email);

            if (_encryptDomain.Encrypt(employee.password) == foundUser.password)
            {
                return _tokenDomain.GenerateJwt(foundUser.email);
            }

            throw new ArgumentException("Invalid email or password");
        }

        public async Task<bool> SaveAsync(Employee employee)
        {
            if (!this.IsValidData(employee.name, employee.last_name)) throw new Exception("La longitud del nombre es inválida");
            if (employee.name?.Length > 20) throw new Exception("La longitud del nombre es muy grande");

            return await _profileInfraestructure.SaveAsync(employee);
        }

        public async Task<int> SignupAsync(Employee employee)
        {
            employee.password = _encryptDomain.Encrypt(employee.password);

            if (!this.IsValidData(employee.name, employee.last_name))
            {
                return 0;
            }

            return await _profileInfraestructure.SignupAsync(employee);
        }

        public Task<bool> UpdateAsync(int id, Employee employee)
        {
            if (!this.IsValidData(employee.name, employee.last_name)) throw new Exception("La longitud del nombre es inválida");
            return _profileInfraestructure.UpdateAsync(id,employee);
        }

        private bool IsValidData(string? name, string? last_name)
        {
            if (name?.Length < 3 || last_name?.Length < 3) return false;
            return true;
        }
    }
}
