package com.pattern.ch14_facade.main;

import com.pattern.ch14_facade.system.Facade;

public class FacadeMain {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.process();

        //HelpSystme01은 default 제한자로 되어있으므로, 해당 패키지에서만 접근이 가능하다. 이 소리는,
        //해당 클래스에 요청을 하기 위해서는 Facade 클래스를 통해서만 요청이 가능하다는 것으로서,
        //HelpSystem01의 변화는 외부에 영향을 주지 않으며, 외부에서는 HelpSystem01이 무슨 작업을 하는 지 모르는 상태에서 그냥 메시지를 통해서만
        //협력이 가능하다는 이야기이다. 또한, 이런 분리는 객체들의 결합도를 느슨하게 해준다는 이점이 있고, 캡슐화 역할도 가지고 있다.
        //HelpSystem01 helpSystem01 = new HelpSystem01();
    }
}
