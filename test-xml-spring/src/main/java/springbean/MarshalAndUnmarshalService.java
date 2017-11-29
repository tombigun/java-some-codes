package springbean;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.oxm.Marshaller;

import org.springframework.oxm.Unmarshaller;

import org.springframework.stereotype.Component;


@Component

public class MarshalAndUnmarshalService {

    private Marshaller marshaller;//注入Marshaller

    private Unmarshaller unmarshaller;//注入Unmarshaller


    @Autowired

    public void setMarshaller(Marshaller marshaller) {

        this.marshaller = marshaller;

    }

    public Marshaller getMarshaller() {

        return marshaller;

    }

    @Autowired

    public void setUnmarshaller(Unmarshaller unmarshaller) {

        this.unmarshaller = unmarshaller;

    }

    public Unmarshaller getUnmarshaller() {

        return unmarshaller;

    }


}