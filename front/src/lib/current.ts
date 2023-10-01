import { Builder } from "builder-pattern";
import _api, { Api, API_URL } from './api';
import { Storage } from "./support";
import { derived, readable, writable } from "svelte/store";
import type { Readable } from 'svelte/store';
import type { Current, Authentication, Member, Link, LinkMenu, Guild } from "./domain";


const authentication = (() => {
    const authorization = Storage.get<Authentication>('authentication');
    Api.authorization = authorization?.token;
    const { subscribe, set } = writable<Authentication | undefined>(authorization);
    const signIn = (token: string) => {
        Api.authorization = token;
        retrive();
    };
    const signOut = () => {
        Storage.reset('authentication');
        Storage.reset('token');
        Storage.reset('avatar');
        Api.authorization = undefined;
        set(undefined);
    };

    const retrive = () => {
        _api.get<Authentication>('authentication').one().then(it => {
            // console.log('before:: ' + JSON.stringify(it));
            if (it?.principal?.avatar && !it.principal.avatar?.startsWith("http")) {
                it.principal.avatar = API_URL + "/image/" + it.principal.avatar;
                console.log('after:: ' + it);
            }

            console.log('---------> Avatar: ' + it.principal.avatar);

            Storage.set('authentication', it);
            set(it);
        });
    };

    return { subscribe, retrive, signIn, signOut };
})();


interface Route {
    id?: string,
    path: RegExp,
    requireAuth?: boolean;
    requireOwner?: boolean;
    component?: () => Promise<typeof import("*.svelte")>,
};

const routes: Route[] = [
    {   /* log in  */
        path: /^login/,
        component: async () => await import('../routes/login/+page.svelte')
    },
    {  /* 의미 없는 url은 무조건 home 으로  -- disabled /./*/
        path: /^0$/,
        component: async () => await import("../routes/+page.svelte")
    },
];

const links: Link[] = [
    { href: "/", title: "Home" },
];

const initialize = Builder<Current>()
    // .hash(location.hash)
    .build();

const RootGguild = readable<Guild>(undefined, set => {
    _api.get<Guild>("guild").path('0').one().then(guild => set(guild));
});


const current = () => {

    const canRefesh = (event: HashChangeEvent): boolean => {
        return true;
    }

    const { subscribe, set } = writable<Current>(initialize);
    let _current: Current;
    subscribe(_it => _current = _it);

    let member: Member | undefined;
    authentication.subscribe(_it => member = _it?.principal);
    
    if (typeof window !== 'undefined') {
        window.onhashchange = async (e: Event) => {
            if (!window.location.hash) {
                window.location.hash = "#/";
                return;
            }
            const hash = window.location.hash.substring(2);
            const route: Route = routes.find(_ => _.path?.test(hash))!;
    
    
            // console.log("routes.find--------------->>", route);
            const groups = route?.path.exec(hash)?.groups;
    
            _current.hash = window.location.hash;
            
            if (route?.component) _current.component = (await route.component()).default;
    
            set(_current);
    
        };
    
        //window.addEventListener("hashchange", onhashchange);
        window.dispatchEvent(new HashChangeEvent('hashchange'));
        
    }
    return { subscribe };
};


const root = current();

const path: Readable<string> = derived(root, (_current) => {
   
});


const paths = derived([root, RootGguild], ([_root, _guild]) => {
    if (!_guild) return [];
    console.log(_root);
    console.log(_guild);
    // const __ = _root.;

    // const _paths = 

});


const refresh = derived(root, (_root) => {
    console.log("refresh = derived--------->>", _root.refresh);
    return _root.refresh;
});


interface Is {
    auth: boolean;
}

const is = derived([root, authentication], ([_root, _auth]): Is => {
    const auth = !!(_auth?.principal?.id);
    console.log('auth = ' + _auth?.principal);
    return { auth };
});



export { root as default, path, paths, authentication, is, refresh };

