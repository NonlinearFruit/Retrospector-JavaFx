using Microsoft.Extensions.DependencyInjection;
using Retrospector.Core.Crud.Interfaces;
using Retrospector.Core.Crud.Models;
using Retrospector.Core.Search.Interfaces;
using Retrospector.DataStorage;
using Retrospector.DataStorage.Factoids;
using Retrospector.DataStorage.Factoids.Interfaces;
using Retrospector.DataStorage.Medias;
using Retrospector.DataStorage.Medias.Interfaces;
using Retrospector.DataStorage.Reviews;
using Retrospector.DataStorage.Reviews.Interfaces;
using Retrospector.DataStorage.Search;
using Retrospector.DataStorage.Search.Interfaces;

namespace Retrospector.Maui.Setup
{
    public class Startup
    {
        public DatabaseConfiguration DatabaseConfig { get; set; }

        public Startup(DatabaseConfiguration databaseConfig)
        {
            DatabaseConfig = databaseConfig;
        }

        public IServiceCollection ConfigureServices(IServiceCollection services)
        {
            return services
                .AddTransient<IDatabaseContext, DatabaseContext>()
                .AddTransient<IMediaReducer, MediaReducer>()
                .AddTransient<IFactoidReducer, FactoidReducer>()
                .AddTransient<IReviewReducer, ReviewReducer>()
                .AddTransient<IMediaMapper, MediaMapper>()
                .AddTransient<IMediaTypeMapper, MediaTypeMapper>()
                .AddTransient<IReviewMapper, ReviewMapper>()
                .AddTransient<IFactoidMapper, FactoidMapper>()
                .AddTransient<ISearchFilterBuilder, SearchFilterBuilder>()
                .AddTransient<ILeafExpressionBuilder, LeafExpressionBuilder>()
                .AddTransient<ISearchDataGateway, SearchDataGateway>()
                .AddTransient<ICrudDataGateway<Media>, MediaDataGateway>()
                .AddTransient<ICrudDataGateway<Review>, ReviewDataGateway>()
                .AddTransient<ICrudDataGateway<Factoid>, FactoidDataGateway>()
                .AddSingleton<DatabaseConfiguration>(DatabaseConfig);
        }
    }
}