import { AngularClientSpaPage } from './app.po';

describe('angular-client-spa App', () => {
  let page: AngularClientSpaPage;

  beforeEach(() => {
    page = new AngularClientSpaPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
