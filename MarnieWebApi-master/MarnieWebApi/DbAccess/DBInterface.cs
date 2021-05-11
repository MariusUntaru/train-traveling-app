using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MarnieWebApi.DbAccess
{
    interface DBInterface<T>
    {
        //T Get(int id);
        T GetWithRelations(int id);
        //ICollection<T> GetAll();
        ICollection<T> GetAllWithRelations();

        void Insert(T item);
        void Update(T item);
        void Delete(int id);
    }
}
