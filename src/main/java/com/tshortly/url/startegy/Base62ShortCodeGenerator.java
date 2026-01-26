package com.tshortly.url.startegy;

import com.tshortly.common.utlity.Base62;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component("base62ShortCodeGenerator")
public class Base62ShortCodeGenerator implements ShortCodeGenerator{
    private final SequenceProvider sequenceProvider;
    @Override
    public String generate() {
        return Base62.encode(sequenceProvider.next());
    }
}