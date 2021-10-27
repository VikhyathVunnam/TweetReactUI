import * as actionType from './actionType';
import axios from 'axios';

export const registerUser = (userData) => {
    return {
        type: actionType.REGISTER_USER,
        userInfo: userData
    };
}

export const authStart=()=>{
    return{
        type: actionType.AUTH_START
    }
}

export const saveUser = () => {
    return dispatch => {
        axios.post('http://localhost:8080/api/tweets/register').then(response => {
            if (response.status === 200) {
                dispatch(registerUser(response.data));
            }
        })
    }
}

export const authFail = (err) => {
    return {
        type: actionType.AUTH_FAILED,
        error: err
    }
}

export const authSuccess = (token, userId) => {
    return {
        type: actionType.AUTH_SUCCESS,
        token: token,
        userId: userId,
    }
}

export const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('expireIn');
    localStorage.removeItem('userId');
    return {
        type: actionType.AUTH_LOGOUT
    };
};

export const checkAuthTimeout = (expirationTime) => {
    return dispatch => {
        setTimeout(() => {
            dispatch(logout());
        }, expirationTime * 1000);
    };
};

export const login = (data) => {
    return dispatch => {
        dispatch(authStart());
        axios.post('http://localhost:8080/api/tweets/login', data).then(response => {
            localStorage.setItem('token', response.data.token);
            localStorage.setItem('expireIn', new Date(response.data.expireIn));
            localStorage.setItem('userId', response.data.userId);
            dispatch(checkAuthTimeout(new Date(response.data.expireIn).getTime()));
            dispatch(authSuccess(response.data.token, response.data.userId))
        }).catch(err => {
            dispatch(authFail(err.response.data));
        })
    }
}

export const authCheckState=()=>{
    return dispatch=>{
        const token = localStorage.getItem('token');
        if(!token){
            dispatch(logout());
        }
        else{
            const expireIn = new Date(localStorage.getItem('expireIn'));
            if(expireIn <= new Date()){
                dispatch(logout());
            }
            else{
                const userId = localStorage.getItem('userId');
                dispatch(authSuccess(token, userId));
                dispatch(checkAuthTimeout(expireIn.getTime()));
            }
        } 
    }
}