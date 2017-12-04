package br.unialfa.teste.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unialfa.teste.model.vo.Pessoa;
import br.unialfa.teste.util.HibernateUtil;

public class PessoaDAO {
	private SessionFactory sf;
	private Session session;

	public PessoaDAO() {

	}

	public void cadastrarOuEditar(Pessoa p) {
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();

			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(p);
			session.flush();
			tx.commit();
		} catch (SessionException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Pessoa> listar(String query) {
		List<Pessoa> lista = new ArrayList<Pessoa>();

		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			lista = session.createQuery(query).getResultList();
		} catch (SessionException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}

	public void remover(long id) {
		Pessoa p = this.recuperar(id);
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			Transaction tx = session.beginTransaction();
			if (p.getId() != 0) {
				session.delete(p);

			} else {
				throw new SessionException("Falha ao recuperar a Pessoa.");
			}
			session.flush();
			tx.commit();
		} catch (SessionException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Pessoa recuperar(long id) {
		Pessoa p = new Pessoa();
		p.setId(0);

		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			p = session.get(Pessoa.class, id);

		} catch (SessionException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return p;
	}
}
