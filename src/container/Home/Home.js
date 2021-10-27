import React, { Component } from 'react';
import SideNav from '../../component/sideNav/SideNav';
import PostTweet from '../../component/addTweet/PostTweet';
import * as actionType from '../../store/action/index';
import Tweet from '../../component/tweet/Tweet';
import { connect } from 'react-redux';

import './Home.css';

class Home extends Component {

    componentDidMount() {
        this.props.getTweets(this.props.token);
    }

    render() {
        return (
            <div className="home_page">
                <SideNav />
                <div>
                    <PostTweet/>
                    <div>
                        {
                            this.props.tweets.map(data => <Tweet key={data._id} data={data} />)
                        }
                    </div>
                </div>
            </div>
        )
    }
}

const mapDispatchToprops = dispatch => {
    return {
        getTweets: (token) => dispatch(actionType.getAllTweets(token))
    }
}

const mapStateToProps = state => {
    return {
        token: state.auth.token,
        tweets: state.tweet.tweetData
    }
}

export default connect(mapStateToProps, mapDispatchToprops)(Home);