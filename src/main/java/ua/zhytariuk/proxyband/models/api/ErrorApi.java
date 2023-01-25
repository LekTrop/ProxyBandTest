package ua.zhytariuk.proxyband.models.api;

import lombok.Builder;
import lombok.Data;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Data
@Builder(toBuilder = true)
public class ErrorApi {

    private String message;
    private int errorCode;
}
