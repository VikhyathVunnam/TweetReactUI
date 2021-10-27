import * as actionType from '../action/actionType';

const initialState = {
    tweetData: []
}

const updateData=(state, action)=>{
    return{
       ...state,
       tweetData: action.tweetData
    };
}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case actionType.GETALL_TWEETS: return updateData(state,action);
       default: return state;
    }
}

export default reducer;