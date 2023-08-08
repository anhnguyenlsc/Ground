import { Builder } from 'builder-pattern';
import type { Member } from './domain/index';

export const API_URL = `${import.meta.env.VITE_API}`;
// console.log("env api ------------------->>", API_URL);

var policies = [
  // Referer will never be set.
  'no-referrer',

  // Referer will not be set when navigating from HTTPS to HTTP.
  'no-referrer-when-downgrade',

  // Full Referer for same-origin requests, and no Referer for cross-origin requests.
  'same-origin',

  // Referer will be set to just the origin, omitting the URL's path and search.
  'origin',

  // Referer will be set to just the origin except when navigating from HTTPS to HTTP,
  // in which case no Referer is sent.
  'strict-origin',

  // Full Referer for same-origin requests, and bare origin for cross-origin requests.
  'origin-when-cross-origin',

  // Full Referer for same-origin requests, and bare origin for cross-origin requests
  // except when navigating from HTTPS to HTTP, in which case no Referer is sent.
  'strict-origin-when-cross-origin',

  // Full Referer for all requests, whether same- or cross-origin.
  'unsafe-url'
];

type Method = 'get' | 'post' | 'put' | 'delete';

interface ApiRequest {
  headers: Headers;
  method: Method;
  url: URL;
  data: FormData | URLSearchParams | object | null;
}

export class Api<T extends object> {

  constructor(method: Method) {
    this.request.method = method;
    // this.request.headers.append('referrerPolicy', 'unsafe-url');
    this.request.headers.append('Accept', 'application/json');
    this.request.headers.append('content-type', "application/json;charset=UTF-8");
    if (Api.authorization)
      this.request.headers.append('Authorization', Api.authorization);
    this.request.data = (method == 'get') ? new URLSearchParams() : new FormData();
  }

  request: ApiRequest = { headers: new Headers(), method: 'get', url: new URL(API_URL), data: null }

  static authorization: string | undefined;

  header = (k: string, v: string) => {
    this.request.headers.append(k, v);
    return this;
  }

  path = (_path: string) => {
    // console.log('before ::', this.request.url);
    const href = this.request.url.href.endsWith('/') ? this.request.url.href : this.request.url.href + '/';
    this.request.url = new URL(_path, href);
    // console.log('after ::', this.request.url);
    return this;
  }

  data = (key: string, value: string) => {
    if (this.request.data instanceof URLSearchParams) {
      this.request.data.append(key, value);
      // console.log('data------------>>', this.request.data.toString());
    }
    else if (this.request.data instanceof FormData) {
      this.request.data.append(key, value);
      // console.log('data------------>>', this.request.data);
    }
    return this;
  }

  set = (_: T) => {
    // console.log('set data ----------->>', JSON.stringify(_));
    this.request.data = _;
    return this;
  }

  res = async (): Promise<Response> => {

    //RequestInit
    const _builder = Builder<RequestInit>().method(this.request.method).headers(this.request.headers);
    if (this.request.method == 'get' || this.request.method == 'delete') {
      //console.log('this.request.method...............................', this.request.method);
      if (this.request.data instanceof URLSearchParams) {
        this.request.data.forEach((v, k, p) => this.request.url.searchParams.append(k, v));
      }
    } else {
      if (this.request.data instanceof URLSearchParams || this.request.data instanceof FormData) {
        _builder.body(this.request.data);
      }
      else {
        _builder.body(JSON.stringify(this.request.data));
      }
    }
    if (this.request.method != 'get' && !Api.authorization) throw new Error("Users who are not logged in will not be able to perform the request..");
    // console.log('request.url--->>', this.request.url.toString());
    // console.log('request.data--->>', this.request.data);
    return await fetch(this.request.url.toString(), _builder.build());
  }

  
  one = (): Promise<T> => this.res().then(async res => {
    if (res.ok) return await res.json();
    throw new Error(res.statusText);
  });

  all = (): Promise<T[]> => this.res().then(async res => {
    if (res.ok) return await res.json();
    throw new Error(res.statusText);
  });

}


const _api = {
  get: <T extends object>(url: string) => new Api<T>('get').path(url),
  post: <T extends object>(url: string) => new Api<T>('post').path(url),
  put: <T extends object>(url: string) => new Api<T>('put').path(url),
  delete: <T extends object>(url: string) => new Api<T>('delete').path(url),
};

export default _api;

export const memberApi = {
  get: async (id: string) => _api.get<Member>("member").path(id).one(),
  post: async (m: Member) => _api.post<Member>("member").set(m).one(),
  put: async (m: Member) => _api.put<Member>("member").set(m).one(),
  delete: async (id: string) => _api.delete<Member>("member").path(id).res(),
  all: async (id: string) => _api.get<Member>("members").data("ref", id).all()
};