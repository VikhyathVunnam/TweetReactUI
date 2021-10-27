import React from 'react';
import { connect } from 'react-redux';
import './PostTweet.css';
import axios from 'axios';

const PostTweet = React.memo(props => {

    const message = React.createRef();

    const changeHeightHandler=(e)=>{
       e.target.style.height = 'inherit';
       e.target.style.height = `${e.target.scrollHeight}px`;
    }

    const postTweet = () =>{
        if(message.current.value.trim() !== ' '){
            const data ={
                message: message.current.value,
                userId: props.userId
            }
            axios.post('http://localhost:8080/api/tweets/add', data, {
                headers:{
                    'Authorization': `Bearer ${props.token}`
                }
            }).then(respose=>{
                console.log(respose.data);
                let x = document.getElementById("snackbar");
                x.className = "show";
                setTimeout(() => {
                    x.className = x.className.replace("show","");
                }, 5000);
                message.current.value = "";
            }).catch(err=>{
                console.log(err);
            })
        }
    }

    return (
        <div className="postCard">
            <h2>Home</h2>
            <div className="post_header">
                <img className="avatar" src="https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png" alt="user_logo" />
                 <strong>{props.userId}</strong>
            </div>
            <textarea ref={message} className="tweet_area"  onChange={(e)=>{changeHeightHandler(e)}} placeholder="what's hapenning ?" maxLength="144"/>
            <button className="btn" onClick={()=>postTweet()}>Tweet</button>
            <div id="snackbar">Tweet posted</div>
        </div>)
});

const mapStateToProps = state => {
    return {
        userId: state.auth.userId,
        token: state.auth.token
    }
}

export default connect(mapStateToProps, null)(PostTweet);