import Login      from "@/views/Login.vue";
import Register   from "@/views/Register.vue";
import Library    from "@/views/Library.vue";
import newBook    from "@/views/NewBook.vue";
import Pages      from "@/Pages.vue";
 
const routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/',
    name: 'pages',
    component: Pages,
    redirect: '/library',
    children: [
      {
        path: 'library',
        name: 'library',
        component: Library
      },
      {
        path: 'new',
        name: 'newBook',
        component: newBook
      }
    ]
  }
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
