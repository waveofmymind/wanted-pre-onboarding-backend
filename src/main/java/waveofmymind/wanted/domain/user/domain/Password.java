package waveofmymind.wanted.domain.user.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import waveofmymind.wanted.global.error.exception.InvalidPasswordException;
import waveofmymind.wanted.global.security.PasswordEncryptor;

import java.io.IOException;
import java.util.Objects;

@Getter
@Embeddable
@JsonSerialize(using = Password.PasswordSerializer.class)
@JsonDeserialize(using = Password.PasswordDeserializer.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    @Column(name = "password")
    private String value;

    public Password(String value) {
        validate(value);
        this.value = PasswordEncryptor.sha256Encrypt(value);
    }

    public void validate(String value) {
        if (value.length() < 8) {
            throw new InvalidPasswordException();
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static class PasswordSerializer extends JsonSerializer<Password> {
        @Override
        public void serialize(Password password, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(password.getValue());
        }
    }

    public static class PasswordDeserializer extends JsonDeserializer<Password> {

        @Override
        public Password deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new Password(p.getText());
        }
    }
}
