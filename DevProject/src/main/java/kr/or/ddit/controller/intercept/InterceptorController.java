package kr.or.ddit.controller.intercept;

public class InterceptorController {

	/*
	 * 17장 인터셉터 
	 * 	- 인터셉터는 웹 앺ㄹ리케이션 내에서 특정한 URL 호출을 가로채는 역할을 한다 
	 * 	
	 * 
	 * 1. 인터셉터 설명 
	 * 	
	 * 		필터와 인터셉터
	 * 		- 서블릿 기술의 필터와 스프링 MVC의 인터셉터는 특정 URI에 접근할 때 제어하는 용도로 사용된다는 공통점이 있다 .
	 * 		하지만 실행 시점에 속하는 영역(Context)에 차이점이 있다 .
	 * 		인터셉터의 경우 스프링에서 관리하기 때문에 스프링 내의 모든 객체에 접근이 가능하지만 필터는 웹 애플리케이션 영역 내의 자원들은 활용 할수 있지만 
	 * 		스프링 내의 객체에는 접근이 불가능하다
	 * 
	 *  	스프링 AOP와 인터셉터
	 *  	- 특정 객체 동작으 사전 혹은 사후 처리는 AOP 기능을 활용할 수 있지만 콘트롤러의 처리는 인터셉터를 활용하는 경우가 더 많다 
	 *  	AOP의 어드바이스와 인터셉터의 가장 큰 차이는 파라미터의 차이라고 할 수있다.
	 * 		어드바이스의 경우 JoinPoint나 ProceedingJoinPoint 등을 활용해서 호출 대상이 되는 메소드의 파라미터 등을 처리하는 방식이다 
	 * 		인터셉터는 필터와 유사하게 HttpServletRequest, HttpServletResponse를 파라미터로 받는 구조이다 
	 * 
	 * 		HandlerInterceptorAdapter 클래스 
	 * 		- HandlerInterceptorAdapter는 HandlerInterceptor를 쉽게 사용하기 위해서 인터페이스의 메소드를 미리 구현한 클래스이다 
	 * 
	 * 		HandlerInterceptor의 메소드는 아래와 같다 
	 * 
	 * 		- preHandle
	 * 		> 지정된 컨트롤러의 동작 이전에 가로채는 역할로 사용 
	 * 		- postHandle
	 * 		> 지정된 컨트롤러의 동작 후에 처리, DispatcherServlet이 화면을 처리하기 전에 동작
	 * 		- afterCompletion
	 * 		> DispatcherServlet의 화면 처리가 완료된 상태에서 처리한다 
	 * 
	 * 2. 인터셉터 구현 
	 * 	
	 * 		클라이언트의 요청을 처리하다 보면 요청 경로마다 접근 제어를 다르게 하거나, 특정 URL에 대한 접근 내역을 기록하고 싶을때가 있다 
	 * 		이런 기능은 특정 컨트롤러에 종속되기 보다 여러 컨트롤러에서 공통적으로 적용되는 기능들이라 할 수 있다. 이런 기능을 각 컨트롤러에서 개별적으로 구현하면 
	 * 		중복 코드가 발생하므로, 코드 중복 없이 기존의 컨트롤러에 수정을 가하지 않고 적용할 수 있는 방법이 필요. 
	 * 		지금까지 배운 내용들을 떠올려보면 이를 해결할 수있는 방식이 2가지 있음
	 * 
	 * 		1. Filter라는 서블릿 스펙에 따른 객체를 사용하는 방법 
	 * 			필터를 통해 DispatcherServlet이나 컨트롤러에게 요청이 위임되기 전/후에 공통적인 
	 * 			어떤 기능을 수행하도록 하면, 기존 컨트롤러나 DispatcherServlet에 어떤 수정사항을 가하거나 코드 중복없이 이슈를 해결 할 수 있다 
	 * 			그러나 이 방법은 한가지 단점이 있음. Filter가 DispatcherServlet보다 먼저 객체가 생성되고 스프링 컨테이너 밖에 존재하기 때문에 
	 * 			컨테이너를 통한 DI를 받을 수 없다는 것. 물론 아예 불가능하진 않음 . 설정과 같은 부분이 복잡 DelegatingFilterProxy라는 필터를 사용하면 
	 * 			필터링 작업을 스프링 컨테이너내에 존재하는 빈에게 위험할 수도 있음. 해당   DelegatingFilterProxy라는 필터는 원래 필터 기반의 
	 * 			보안 처리를 지원하는 Spring Security 프레임워크에서 제공했던 필터인데 하도 널리 쓰이기 시작하면서 
	 * 			아예 스프링 코어 웹 모듈로 편입된 타입. 이 필터를 이용해서 스프링 빈에게 필터링을 위임하는 방법도 몇가지 불편한 점이 있다 
	 * 			반드시 위임을 받을 빈은 Filter를 구현하고 있어야하고, 루트 컨텍스트에서 관리되는 빈이어야만 한다는 것
	 * 
	 * 		2. AOP 방법론에 따라 공통 기능을 정의한 Advice를 구현하고, pointcut을 통해 적절한 target  컨트롤러를 골라낸 다음 두 설정으로 
	 * 			Aspect를 생성하여 런타임에 컨트롤러와 위빙하도록 하는 방법을 생각해볼 수 있음 .실제 보안 프레임워크들에서도 AOP 방법론에 따라 
	 * 			위빙을 위한 어노테이션을 활용하고  있음 
	 * 
	 * 		그렇지만 1/2번째 모두 우리가 처리하고 싶은 기능을 구현하는데는 제약이 따른다 
	 * 		우리가 지금 처리하고 싶은 기능은 특정 URL에 대한 접근 내역을 기록하거나 특정 경로에 대한 접근 제어를 하는 등 웹 이라는 공간과 환경에 제한된
	 * 		공통 기능을 처리하고 싶은 것 
	 * 		AOP는 너무 범용적인 방법이라 할 수 있고, Filter 방식은 제약이많음 (사용할 자원의 환경이 다름)
	 * 		이러한 경우에 사용하기 위한 전략으로 스프링은 HandlerInterceptor라는 추상화를 제공하고 있으며, 이를 사용하면 Spring MVC에 맞게 
	 * 		공통 기능을 다수의 URL에 적용할 수 있게 된다 
	 * 		HandlerInterceptor 인터페이스를 사용하면 아래와 같은 시점에 대해 공통 기능을 넣을 수 있다 
	 * 		>컨트롤러 실행 전(preHandle)
	 * 		>컨트롤러 실행 후, 아직 뷰를 실행하기 전 단계 (postHandle)
	 * 		> 뷰를 실행한 후(afterCompletion)
	 * 
	 * 		preHandle()메소드는 컨트롤러 객체를 실행하기 전에 필요한 기능을 구현할 때 사용되며, handler 파라미터는 웹요청을 처리할 컨트롤러 객체
	 * 		이 메소드를 사용하면 컨트롤러를 실행하기 전에 컨트롤러에서 필요로 하는 정보를 생성하거나 접근 권한이 없는 경우 리턴값으로 false를 반환하여 컨트롤러가 
	 * 		실행되지않도록 하는 작업이 가능해진다 
	 * 		
	 * 		postHandler() 메소드는  컨트롤러가 정상적으로 실행된 이후에 추가 기능을 구현하는데 사용되는데, 만약 컨트롤러에서 예외가 발생했다면 
	 * 		postHandler() 메소드는 실행되지 않는다.
	 * 
	 * 		afterCompletion() 메소드는 클라이언트에 뷰를 전송한 뒤에 실행되며, 만약 컨트롤러를 실행하는 과정에서 예외가 발생했다면, 이 메소드의 네번째 파라미터로 전달된다.
	 * 		예외가 발생하지 않느 경우는 null값이 들어올 것. 따라서 컨트롤러 실행 이후 예기치 않은 예외에 대해 로깅을 한다거나 실행 시간을 기록하는 등의 후 처리를 하기에 
	 * 		적절한 메소드임. 정리하면, HandlerInterceptor는 세가지 메소드를 통해 AOP방법론에서 시점에 따른 여러 advice 들의 역할 하나의 
	 * 		HandlerInterceptor객체가 전달할 수 있는 구조를 갖고있음 
	 * 		
	 * 	3. 인터셉터 설정 
	 * 	- 인터셉터 클래스를 정의하고 스프링 웹 설정 파일에 인터셉터를 지정한다 
	 * 
	 * 		인터셉터 지정 
	 * 		- servlet-context.xml 에서 설정 
	 * 		> loginInterceptor 아이디로 빈등록 
	 * 		> interceptor 태그 설정
	 * 		
	 * 		<beans:bean 태그에 xmlns설정을 아래가 먼저로 진행되게 변경
	 * 		xmlns="http://www.springframework.org/schema/mvc" 
	 */

}
