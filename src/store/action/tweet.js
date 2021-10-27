import * as actionType from './actionType';
import axios from 'axios';

const getTweets = (data) => {
    return {
        type: actionType.GETALL_TWEETS,
        tweetData: data
    }
}

export const getAllTweets = (token) => {
    return dispatch => {
        axios.get('http://localhost:8080/api/tweets/all', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }).then(response => {
            console.log(response.data);
            dispatch(getTweets(response.data));
        }).catch(err => {
            console.log(err);
        })

    }
}
