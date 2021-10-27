import React, {useState} from 'react';
import './ForgetPassword.css';

const ForgetPassword = React.memo(props => {
    const emailId = React.createRef();
    const username = React.createRef();
    const password = React.createRef();

    const [unameError, setUnameError] = useState("");
    const [passError, setPasswordError] = useState("");
    const [emailError, setEmailError] = useState("");

    const validate=()=>{
        if(isValid()){
            
        }
    }

    const isValid=()=>{
        const name = username.current.value;
        const pass = password.current.value;
        const email = emailId.current.value;

        let isValid = false;

        if (name.trim() === "") {
            isValid = false;
            setUnameError("user name is required");
        }
        else if (!name.match("^[a-zA-Z ]+$")) {
            isValid = false;
            setUnameError("please enter your user name correctly");
        }

        if (email.trim() === "") {
            isValid = false;
            setEmailError("email is required");
        }
        else if (!email.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)) {
            isValid = false;
            setEmailError("please enter correct email id")
        }

        if (pass.trim() === "") {
            isValid = false;
            setEmailError("password is required");
        }
        else if (pass.length < 8) {
            isValid = false;
            setEmailError("password should containes atleast 8 character")
        }

        return isValid;
    }

    return (
        <div className="forget_pass">
            <h2>Reset your password</h2>
            <div className="form_ele">
                <input ref={emailId} type="email" placeholder="email id" />
                <span className="error">{emailError}</span>
            </div>
            <div className="form_ele">
                <input ref={username} type="text" placeholder="user name" />
                <span className="error">{unameError}</span>
            </div>
            <div className="form_ele">
                <input ref={password} type="password" placeholder="new password" />
                <span className="error">{passError}</span>
            </div>
            <button className="btn">Change Password</button>
        </div>
    );
});

export default ForgetPassword;