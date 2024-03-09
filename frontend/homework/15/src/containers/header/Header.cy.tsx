import React from 'react'
import { Header } from './Header'
import { store } from '../../redux/Store'
import { Provider } from 'react-redux'
import App from '../../App'

describe('<Header />', () => {
  it('renders', () => {
    // see: https://on.cypress.io/mounting-react
    cy.mount(    <Provider store={store}>
      <Header />

    </Provider>)

    cy.contains('Item Lister').should('exist')
  })
})