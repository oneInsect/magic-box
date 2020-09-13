import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/filemgt',
    name: 'FileManagement',
    hidden: true,
    children: [{
      path: 'filemgt',
      component: () => import('@/views/filemgt/index')
    }]
  },

  {
    path: '/files',
    component: Layout,
    redirect: '/files/table',
    name: 'Files',
    meta: { title: 'Files', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'File List',
        component: () => import('@/views/filemgt/files/list'),
        meta: { title: 'File List', icon: 'table' }
      },
      {
        path: 'save',
        name: 'Add File',
        component: () => import('@/views/filemgt/files/save'),
        meta: { title: 'Add File', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'Edit File',
        component: () => import('@/views/filemgt/files/save'),
        meta: { title: 'Edit File', noCache: true },
        hidden: true
      }
    ]
  },

  {
    path: '/categories',
    component: Layout,
    redirect: '/categories/table',
    name: 'Categories',
    meta: { title: 'Categories', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'Category List',
        component: () => import('@/views/filemgt/categories/list'),
        meta: { title: 'Category List', icon: 'table' }
      },
      {
        path: 'save',
        name: 'Add Category',
        component: () => import('@/views/filemgt/categories/save'),
        meta: { title: 'Add Category', icon: 'tree' }
      }
    ]
  },

  {
    path: '/accesskey',
    component: Layout,
    redirect: '/accesskey/table',
    name: 'Accesskey',
    meta: { title: 'Accesskey', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'Accesskey List',
        component: () => import('@/views/filemgt/accesskey/list'),
        meta: { title: 'Accesskey List', icon: 'table' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  mode: 'history', 
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
