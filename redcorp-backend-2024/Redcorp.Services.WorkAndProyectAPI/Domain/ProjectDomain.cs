using Redcorp.Services.WorkAndProyectAPI.Domain.I.Domain;
using Redcorp.Services.WorkAndProyectAPI.Infraestructure.I.Infraestructure;
using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Domain
{
    public class ProjectDomain : IProjectDomain
    {
        public IProjectInfraestructure _projectInfraestructure;

        public ProjectDomain(IProjectInfraestructure projectInfraestructure)
        {
            _projectInfraestructure = projectInfraestructure;
        }

        public async Task<bool> SaveAsync(Project project)
        {
            if (!this.IsValidData(project)) return false;

            return await _projectInfraestructure.SaveAsync(project);
        }

        public bool update(int id, Project project)
        {
            if (!this.IsValidData(project)) return false;

            return _projectInfraestructure.update(id, project);
        }

        public bool delete(int id)
        {
            return _projectInfraestructure.delete(id);
        }

        private bool IsValidData(Project project)
        {
            if (!this.IsValidNameData(project.Name)) throw new Exception("The length of your name is invalid");
            if (!this.IsValidDescriptionData(project.Description)) throw new Exception("The length of your description is invalid");
            if (!this.IsValidStateData(project.State)) throw new Exception("The state is invalid (In Pogress, Completed, To Do, In Revision)");
            return true;
        }

        private bool IsValidNameData(string name)
        {
            if (name.Length < 3) return false;
            if (name.Length > 40) return false;
            return true;
        }

        private bool IsValidDescriptionData(string description)
        {
            if (description.Length < 3) return false;
            if (description.Length > 70) return false;
            return true;
        }

        private bool IsValidStateData(string state)
        {
            if (state == "In Progress" || state == "Completed" || state == "To Do" || state == "In Revision") return true;
            return false;
        }
    }
}
