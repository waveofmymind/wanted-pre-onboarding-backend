package waveofmymind.wanted.domain.user.domain;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import waveofmymind.wanted.global.security.PasswordEncryptor;

import java.io.IOException;

@Getter
@Embeddable
@JsonSerialize(using = Password.PasswordSerializer.class)
@JsonDeserialize(using = Password.PasswordDeserializer.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    private String value;

    public Password(String value) {
        this.value = PasswordEncryptor.sha256Encrypt(value);
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
