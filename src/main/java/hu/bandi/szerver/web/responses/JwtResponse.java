package hu.bandi.szerver.web.responses;


import lombok.Data;

@Data
public class JwtResponse {
    private final String token;
    private final String type = "Bearer";
    private long expiresAtMillies;

    public JwtResponse(final String accessToken, final long millies) {
        token = accessToken;
        this.expiresAtMillies = millies;
    }
}
