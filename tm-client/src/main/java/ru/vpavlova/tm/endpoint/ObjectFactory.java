
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

    private final static QName _Exception_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "Exception");
    private final static QName _AddAllUsers_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "addAllUsers");
    private final static QName _AddAllUsersResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "addAllUsersResponse");
    private final static QName _AddUser_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "addUser");
    private final static QName _AddUserResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "addUserResponse");
    private final static QName _ClearUsers_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "clearUsers");
    private final static QName _ClearUsersResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "clearUsersResponse");
    private final static QName _CreateUser_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "createUser");
    private final static QName _CreateUserResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "createUserResponse");
    private final static QName _CreateUserWithEmail_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "createUserWithEmail");
    private final static QName _CreateUserWithEmailResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "createUserWithEmailResponse");
    private final static QName _FindAllUsers_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "findAllUsers");
    private final static QName _FindAllUsersResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "findAllUsersResponse");
    private final static QName _LockUserByLogin_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "lockUserByLogin");
    private final static QName _LockUserByLoginResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "lockUserByLoginResponse");
    private final static QName _RemoveOneByLogin_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "removeOneByLogin");
    private final static QName _RemoveOneByLoginResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "removeOneByLoginResponse");
    private final static QName _RemoveUser_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "removeUser");
    private final static QName _RemoveUserResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "removeUserResponse");
    private final static QName _SetUserPassword_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "setUserPassword");
    private final static QName _SetUserPasswordResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "setUserPasswordResponse");
    private final static QName _UnlockUserByLogin_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "unlockUserByLogin");
    private final static QName _UnlockUserByLoginResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "unlockUserByLoginResponse");
    private final static QName _UpdateUser_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "updateUser");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://endpoint.tm.vpavlova.ru/", "updateUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.vpavlova.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link AddAllUsers }
     * 
     */
    public AddAllUsers createAddAllUsers() {
        return new AddAllUsers();
    }

    /**
     * Create an instance of {@link AddAllUsersResponse }
     * 
     */
    public AddAllUsersResponse createAddAllUsersResponse() {
        return new AddAllUsersResponse();
    }

    /**
     * Create an instance of {@link AddUser }
     * 
     */
    public AddUser createAddUser() {
        return new AddUser();
    }

    /**
     * Create an instance of {@link AddUserResponse }
     * 
     */
    public AddUserResponse createAddUserResponse() {
        return new AddUserResponse();
    }

    /**
     * Create an instance of {@link ClearUsers }
     * 
     */
    public ClearUsers createClearUsers() {
        return new ClearUsers();
    }

    /**
     * Create an instance of {@link ClearUsersResponse }
     * 
     */
    public ClearUsersResponse createClearUsersResponse() {
        return new ClearUsersResponse();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link CreateUserWithEmail }
     * 
     */
    public CreateUserWithEmail createCreateUserWithEmail() {
        return new CreateUserWithEmail();
    }

    /**
     * Create an instance of {@link CreateUserWithEmailResponse }
     * 
     */
    public CreateUserWithEmailResponse createCreateUserWithEmailResponse() {
        return new CreateUserWithEmailResponse();
    }

    /**
     * Create an instance of {@link FindAllUsers }
     * 
     */
    public FindAllUsers createFindAllUsers() {
        return new FindAllUsers();
    }

    /**
     * Create an instance of {@link FindAllUsersResponse }
     * 
     */
    public FindAllUsersResponse createFindAllUsersResponse() {
        return new FindAllUsersResponse();
    }

    /**
     * Create an instance of {@link LockUserByLogin }
     * 
     */
    public LockUserByLogin createLockUserByLogin() {
        return new LockUserByLogin();
    }

    /**
     * Create an instance of {@link LockUserByLoginResponse }
     * 
     */
    public LockUserByLoginResponse createLockUserByLoginResponse() {
        return new LockUserByLoginResponse();
    }

    /**
     * Create an instance of {@link RemoveOneByLogin }
     * 
     */
    public RemoveOneByLogin createRemoveOneByLogin() {
        return new RemoveOneByLogin();
    }

    /**
     * Create an instance of {@link RemoveOneByLoginResponse }
     * 
     */
    public RemoveOneByLoginResponse createRemoveOneByLoginResponse() {
        return new RemoveOneByLoginResponse();
    }

    /**
     * Create an instance of {@link RemoveUser }
     * 
     */
    public RemoveUser createRemoveUser() {
        return new RemoveUser();
    }

    /**
     * Create an instance of {@link RemoveUserResponse }
     * 
     */
    public RemoveUserResponse createRemoveUserResponse() {
        return new RemoveUserResponse();
    }

    /**
     * Create an instance of {@link SetUserPassword }
     * 
     */
    public SetUserPassword createSetUserPassword() {
        return new SetUserPassword();
    }

    /**
     * Create an instance of {@link SetUserPasswordResponse }
     * 
     */
    public SetUserPasswordResponse createSetUserPasswordResponse() {
        return new SetUserPasswordResponse();
    }

    /**
     * Create an instance of {@link UnlockUserByLogin }
     * 
     */
    public UnlockUserByLogin createUnlockUserByLogin() {
        return new UnlockUserByLogin();
    }

    /**
     * Create an instance of {@link UnlockUserByLoginResponse }
     * 
     */
    public UnlockUserByLoginResponse createUnlockUserByLoginResponse() {
        return new UnlockUserByLoginResponse();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAllUsers }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddAllUsers }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "addAllUsers")
    public JAXBElement<AddAllUsers> createAddAllUsers(AddAllUsers value) {
        return new JAXBElement<AddAllUsers>(_AddAllUsers_QNAME, AddAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAllUsersResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddAllUsersResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "addAllUsersResponse")
    public JAXBElement<AddAllUsersResponse> createAddAllUsersResponse(AddAllUsersResponse value) {
        return new JAXBElement<AddAllUsersResponse>(_AddAllUsersResponse_QNAME, AddAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "addUser")
    public JAXBElement<AddUser> createAddUser(AddUser value) {
        return new JAXBElement<AddUser>(_AddUser_QNAME, AddUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "addUserResponse")
    public JAXBElement<AddUserResponse> createAddUserResponse(AddUserResponse value) {
        return new JAXBElement<AddUserResponse>(_AddUserResponse_QNAME, AddUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearUsers }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ClearUsers }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "clearUsers")
    public JAXBElement<ClearUsers> createClearUsers(ClearUsers value) {
        return new JAXBElement<ClearUsers>(_ClearUsers_QNAME, ClearUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearUsersResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ClearUsersResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "clearUsersResponse")
    public JAXBElement<ClearUsersResponse> createClearUsersResponse(ClearUsersResponse value) {
        return new JAXBElement<ClearUsersResponse>(_ClearUsersResponse_QNAME, ClearUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserWithEmail }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateUserWithEmail }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "createUserWithEmail")
    public JAXBElement<CreateUserWithEmail> createCreateUserWithEmail(CreateUserWithEmail value) {
        return new JAXBElement<CreateUserWithEmail>(_CreateUserWithEmail_QNAME, CreateUserWithEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserWithEmailResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateUserWithEmailResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "createUserWithEmailResponse")
    public JAXBElement<CreateUserWithEmailResponse> createCreateUserWithEmailResponse(CreateUserWithEmailResponse value) {
        return new JAXBElement<CreateUserWithEmailResponse>(_CreateUserWithEmailResponse_QNAME, CreateUserWithEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsers }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindAllUsers }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "findAllUsers")
    public JAXBElement<FindAllUsers> createFindAllUsers(FindAllUsers value) {
        return new JAXBElement<FindAllUsers>(_FindAllUsers_QNAME, FindAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsersResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindAllUsersResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "findAllUsersResponse")
    public JAXBElement<FindAllUsersResponse> createFindAllUsersResponse(FindAllUsersResponse value) {
        return new JAXBElement<FindAllUsersResponse>(_FindAllUsersResponse_QNAME, FindAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockUserByLogin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LockUserByLogin }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "lockUserByLogin")
    public JAXBElement<LockUserByLogin> createLockUserByLogin(LockUserByLogin value) {
        return new JAXBElement<LockUserByLogin>(_LockUserByLogin_QNAME, LockUserByLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockUserByLoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LockUserByLoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "lockUserByLoginResponse")
    public JAXBElement<LockUserByLoginResponse> createLockUserByLoginResponse(LockUserByLoginResponse value) {
        return new JAXBElement<LockUserByLoginResponse>(_LockUserByLoginResponse_QNAME, LockUserByLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveOneByLogin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveOneByLogin }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "removeOneByLogin")
    public JAXBElement<RemoveOneByLogin> createRemoveOneByLogin(RemoveOneByLogin value) {
        return new JAXBElement<RemoveOneByLogin>(_RemoveOneByLogin_QNAME, RemoveOneByLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveOneByLoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveOneByLoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "removeOneByLoginResponse")
    public JAXBElement<RemoveOneByLoginResponse> createRemoveOneByLoginResponse(RemoveOneByLoginResponse value) {
        return new JAXBElement<RemoveOneByLoginResponse>(_RemoveOneByLoginResponse_QNAME, RemoveOneByLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "removeUser")
    public JAXBElement<RemoveUser> createRemoveUser(RemoveUser value) {
        return new JAXBElement<RemoveUser>(_RemoveUser_QNAME, RemoveUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "removeUserResponse")
    public JAXBElement<RemoveUserResponse> createRemoveUserResponse(RemoveUserResponse value) {
        return new JAXBElement<RemoveUserResponse>(_RemoveUserResponse_QNAME, RemoveUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUserPassword }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetUserPassword }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "setUserPassword")
    public JAXBElement<SetUserPassword> createSetUserPassword(SetUserPassword value) {
        return new JAXBElement<SetUserPassword>(_SetUserPassword_QNAME, SetUserPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUserPasswordResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetUserPasswordResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "setUserPasswordResponse")
    public JAXBElement<SetUserPasswordResponse> createSetUserPasswordResponse(SetUserPasswordResponse value) {
        return new JAXBElement<SetUserPasswordResponse>(_SetUserPasswordResponse_QNAME, SetUserPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnlockUserByLogin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UnlockUserByLogin }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "unlockUserByLogin")
    public JAXBElement<UnlockUserByLogin> createUnlockUserByLogin(UnlockUserByLogin value) {
        return new JAXBElement<UnlockUserByLogin>(_UnlockUserByLogin_QNAME, UnlockUserByLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnlockUserByLoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UnlockUserByLoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "unlockUserByLoginResponse")
    public JAXBElement<UnlockUserByLoginResponse> createUnlockUserByLoginResponse(UnlockUserByLoginResponse value) {
        return new JAXBElement<UnlockUserByLoginResponse>(_UnlockUserByLoginResponse_QNAME, UnlockUserByLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.vpavlova.ru/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

}
