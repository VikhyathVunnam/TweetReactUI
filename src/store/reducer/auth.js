import * as actionType from '../action/actionType';

const initialState = {
    token: null,
    userId: null,
    error: null,
    loading: false
}

const authStart = (state) =>{
    return{
        ...state,
        loding: true,
    }
}

const authSuccess=(state, action)=>{
    const updateData ={
        token: action.token,
        userId: action.userId,
        error: null,
        loading: false
    }
    return{
        ...state,
        ...updateData
    }
}
const authFailed=(state, action)=>{
    const updateData={
        error: action.error.errDetails
    }
    return{
        ...state,
        ...updateData
    }
}

const reducer = (state = initialState, action) => {
    switch (action.type) {
       case actionType.AUTH_SUCCESS: return authSuccess(state, action);
       case actionType.AUTH_FAILED: return authFailed(state, action);
       case actionType.AUTH_START: return authStart(state);
       default: return state;
    }
}

export default reducer;