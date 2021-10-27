import React from 'react';
import './Tweet.css';
import moment from 'moment';

const Tweet = React.memo(props => {


    const getHourofPost = (date) => {
        return moment(date).format("DD-MMM-YYYY")
    }
    return (

        <div className="tweetCard">
            <div className="tweet_header">
                <img className="avatar" src="https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png" alt="user_logo" />
                <div className="tweet_userContent">
                    <strong>{props.data.userId}</strong>
                    <small>{getHourofPost(props.data.timeStamp)}</small>
                </div>
            </div>
            <p className="tweet_body">
                {props.data.message}
            </p>
            <div className="tweet_footer">
                <i className="fa fa-thumbs-up" aria-hidden="true">&nbsp;{props.data.likes.length} likes</i>
                <i className="fa fa-comments-o" aria-hidden="true">&nbsp;{props.data.replies} comments</i>
            </div>
        </div>

    );
});

export default Tweet;