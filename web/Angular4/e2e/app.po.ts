import { browser, by, element } from 'protractor';

export class Angular4Page {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('Layout-root h1')).getText();
  }
}
