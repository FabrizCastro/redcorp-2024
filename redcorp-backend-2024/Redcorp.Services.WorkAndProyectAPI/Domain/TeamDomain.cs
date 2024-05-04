using Redcorp.Services.WorkAndProyectAPI.Domain.I.Domain;
using Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Domain
{
    public class TeamDomain : ITeamDomain
    {
        public ITeamInfraestructure _teamInfraestructure;

        public TeamDomain(ITeamInfraestructure teamInfraestructure)
        {
            _teamInfraestructure = teamInfraestructure;
        }

        public async Task<bool> SaveAsync(Team team)
        {
            if (!this.IsValidData(team)) return false;

            return await _teamInfraestructure.SaveAsync(team);
        }

        public bool update(int id, Team team)
        {
            if (!this.IsValidData(team)) return false;

            return _teamInfraestructure.update(id, team);
        }

        public bool delete(int id)
        {
            return _teamInfraestructure.delete(id);
        }

        private bool IsValidData(Team team)
        {
            if (!this.IsValidNameData(team.Name)) throw new Exception("The length of your name is invalid");
            if (!this.IsValidDescriptionData(team.Description)) throw new Exception("The length of your description is invalid");
            return true;
        }

        private bool IsValidNameData(string name)
        {
            if (name.Length < 3) return false;
            if (name.Length > 20) return false;
            return true;
        }

        private bool IsValidDescriptionData(string description)
        {
            if (description.Length < 3) return false;
            if (description.Length > 70) return false;
            return true;
        }

    }
}
