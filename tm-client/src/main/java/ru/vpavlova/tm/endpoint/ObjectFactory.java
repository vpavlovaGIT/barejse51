
package ru.vpavlova.tm.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.vpavlova.tm.endpoint package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindUserByLogin_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "findUserByLogin");
    private final static QName _FindUserByLoginResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "findUserByLoginResponse");
    private final static QName _FindUserOneBySession_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "findUserOneBySession");
    private final static QName _FindUserOneBySessionResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "findUserOneBySessionResponse");
    private final static QName _SetPassword_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "setPassword");
    private final static QName _SetPasswordResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "setPasswordResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.vpavlova.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindUserByLogin }
     * 
     */
    public FindUserByLogin createFindUserByLogin() {
        return new FindUserByLogin();
    }

    /**
     * Create an instance of {@link FindUserByLoginResponse }
     * 
     */
    public FindUserByLoginResponse createFindUserByLoginResponse() {
        return new FindUserByLoginResponse();
    }

    /**
     * Create an instance of {@link FindUserOneBySession }
     * 
     */
    public FindUserOneBySession createFindUserOneBySession() {
        return new FindUserOneBySession();
    }

    /**
     * Create an instance of {@link FindUserOneBySessionResponse }
     * 
     */
    public FindUserOneBySessionResponse createFindUserOneBySessionResponse() {
        return new FindUserOneBySessionResponse();
    }

    /**
     * Create an instance of {@link SetPassword }
     * 
     */
    public SetPassword createSetPassword() {
        return new SetPassword();
    }

    /**
     * Create an instance of {@link SetPasswordResponse }
     * 
     */
    public SetPasswordResponse createSetPasswordResponse() {
        return new SetPasswordResponse();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserByLogin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindUserByLogin }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "findUserByLogin")
    public JAXBElement<FindUserByLogin> createFindUserByLogin(FindUserByLogin value) {
        return new JAXBElement<FindUserByLogin>(_FindUserByLogin_QNAME, FindUserByLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserByLoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindUserByLoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "findUserByLoginResponse")
    public JAXBElement<FindUserByLoginResponse> createFindUserByLoginResponse(FindUserByLoginResponse value) {
        return new JAXBElement<FindUserByLoginResponse>(_FindUserByLoginResponse_QNAME, FindUserByLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserOneBySession }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindUserOneBySession }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "findUserOneBySession")
    public JAXBElement<FindUserOneBySession> createFindUserOneBySession(FindUserOneBySession value) {
        return new JAXBElement<FindUserOneBySession>(_FindUserOneBySession_QNAME, FindUserOneBySession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserOneBySessionResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindUserOneBySessionResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "findUserOneBySessionResponse")
    public JAXBElement<FindUserOneBySessionResponse> createFindUserOneBySessionResponse(FindUserOneBySessionResponse value) {
        return new JAXBElement<FindUserOneBySessionResponse>(_FindUserOneBySessionResponse_QNAME, FindUserOneBySessionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetPassword }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetPassword }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "setPassword")
    public JAXBElement<SetPassword> createSetPassword(SetPassword value) {
        return new JAXBElement<SetPassword>(_SetPassword_QNAME, SetPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetPasswordResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetPasswordResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "setPasswordResponse")
    public JAXBElement<SetPasswordResponse> createSetPasswordResponse(SetPasswordResponse value) {
        return new JAXBElement<SetPasswordResponse>(_SetPasswordResponse_QNAME, SetPasswordResponse.class, null, value);
    }

}
