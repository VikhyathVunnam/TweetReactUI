import React, { Component } from 'react';
import { Route, Switch, BrowserRouter } from 'react-router-dom';
import './App.css';

import Auth from './component/auth/Auth';
import SignUp from './component/signup/SignUp';
import ForgetPassword from './component/auth/forgetPass/ForgetPassword';
import * as actionType from './store/action/index';
import { connect } from 'react-redux';
import Home from './container/Home/Home';

class App extends Component {

  componentDidMount() {
    this.props.onTryAutoSignUp();
    
  }

  render() {
    let routes = (
      <Switch>
        <Route path="/login" component={Auth} />
        <Route path="/signup" component={SignUp} />
        <Route path="/reset" component={ForgetPassword} />
      </Switch>
    );

    if (this.props.isAuth) {
      routes = (<Home />);
    }

    return (
      <div className="App">
        <BrowserRouter>
          {routes}
        </BrowserRouter>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    isAuth: state.auth.token !== null
  };
}

const mapDispatchToState = dispatch => {
  return {
    onTryAutoSignUp: () => dispatch(actionType.authCheckState())
  };
}

export default connect(mapStateToProps, mapDispatchToState)(App);
