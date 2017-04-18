import { InMemoryDbService } from 'angular-in-memory-web-api';
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    let users = [
      {id: 1, username: 'peloxo', password: '123'},
      {id: 2, username: 'caca', password: '321'}
    ];
    return {users};
  }
}
