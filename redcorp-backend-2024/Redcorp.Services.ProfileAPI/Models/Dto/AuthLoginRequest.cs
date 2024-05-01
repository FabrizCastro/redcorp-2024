namespace Redcorp.Services.AuthAndProfileAPI.Models.Dto
{
    public class AuthLoginRequest
    {
        public string email { get; set; }
        public string password { get; set; }
        public string rol { get; set; }
    }
}
