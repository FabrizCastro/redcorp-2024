namespace Redcorp.Services.AuthAndProfileAPI.Domain.I.Domain
{
    public interface ITokenDomain
    {
        string GenerateJwt(string username);
        string ValidateJwt(string token);
    }
}
