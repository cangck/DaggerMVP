#1.从零搭建MVP架构
    
    1.添加全局的配置提供类
        1:AppComponent
            1.StorageModule:存储相关
            2.BackendModule:本地操作类相关
            3.FrontendModule:对外操作的模块
                例子:网络请求，第三方SDK调用
                
    全局使用的Dagger依赖:
    @Component(modules = {BackendModule.class, StorageModule.class, FrontendModule.class})
    public interface AppComponent {
    
        Application application();
    
        FileCache getFileCache();
    
        Gson gson();
    
        IRepositoryManager repositoryManager();
        
        //使用组件Builder来实现Application的注入
        @Component.Builder
        interface Builder {
            //使用组件来指明当前的的方法是返回实例
            @BindsInstance
            Builder application(Application application);
    
            Builder frontendModule(FrontendModule frontendModule);
    
            //        Builder backendModule(BackendModule backendModule);
            Builder storageModule(StorageModule storageModule);
    
            AppComponent build();
        }
    }
    //使用了静态static,@Binds,abstract在Dagger Module中的使用``
    @Module
    public abstract class BackendModule {
    
    
        @Singleton
        @Provides
        static Gson provideGson(Application application) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            return gsonBuilder.create();
        }
    
        @Singleton
        @Provides
        static File provideRxCacheDirectory(Application application) {
            return DataHelper.getCacheFile(application);
        }
    
        //使用Binds注解来绑定接口和实现类
        @Binds
        abstract IRepositoryManager bindRepositoryManager(RepositoryManager repositoryManager);
    }

                
#2.实现Responsity仓库本地操作部分的搭建
#3.实现网络部分的架构
    Retrofit封装
    https配置
    请求头配置
    缓存配置
    数据加密库的使用
    