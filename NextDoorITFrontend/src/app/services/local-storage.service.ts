import { Injectable } from '@angular/core';

import { GlobalConstants } from '../global-constants';

@Injectable({ providedIn: 'root' })
export class LocalStorageService {
  ttl = GlobalConstants.ttl

  constructor() { }

  public setItem(key: string, data: any): void {
    const now = new Date()
    // `item` is an object which contains the original value
    // as well as the time when it's supposed to expire
    const item = {
      value: data,
      expiry: now.getTime() + this.ttl.THIRTY_MIN,
    }
    localStorage.setItem(key, JSON.stringify(item));
  }

  public getItem(key: string): any {
    const itemStr = localStorage.getItem(key)
    // if the item doesn't exist, return null
    if (!itemStr) {
      return null
    }
    const item = JSON.parse(itemStr)
    const now = new Date()
    // compare the expiry time of the item with the current time
    if (now.getTime() > item.expiry) {
      // If the item is expired, delete the item from storage
      // and return null
      localStorage.removeItem(key)
      return null
    }
    return item.value
  }

  public removeItem(key: string): void {
    localStorage.removeItem(key);
  }

  public clear() {
    localStorage.clear();
  }
}